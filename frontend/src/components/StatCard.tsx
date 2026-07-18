interface StatCardProps {
    label: string;
    value: string;
}

export default function StatCard({ label, value }: StatCardProps) {
    return (
        <div
            style={{
                background: "#ffffff",
                border: "1px solid #e2e8f0",
                borderRadius: "14px",
                padding: "18px 20px",
                display: "flex",
                flexDirection: "column",
                gap: "8px",
                boxShadow: "0 2px 6px rgba(15, 23, 42, 0.06)",
                transition: "transform 0.2s ease, box-shadow 0.2s ease",
                cursor: "default",
            }}
        >
            <span
                style={{
                    color: "#64748b",
                    fontSize: "0.85rem",
                    fontWeight: 600,
                    textTransform: "uppercase",
                    letterSpacing: "0.04em",
                }}
            >
                {label}
            </span>

            <span
                style={{
                    color: "#1e293b",
                    fontSize: "1.8rem",
                    fontWeight: 800,
                    lineHeight: 1,
                }}
            >
                {value}
            </span>
        </div>
    );
}